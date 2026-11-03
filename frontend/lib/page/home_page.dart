import 'package:flutter/material.dart';
import 'package:frontend/page/add_movies_page.dart';
import 'package:frontend/page/movies_page.dart';

class HomePage extends StatelessWidget {
  const HomePage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('CineVault')),
      body: Column(
        children: [
          ListTile(
            leading: Icon(Icons.movie),
            title: Text('My Movies'),
            trailing: Icon(Icons.chevron_right),
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => const MoviesPage()),
              );
            },
          ),
          Text('TV Series'),
          ListTile(
            leading: Icon(Icons.search),
            title: Text('Add Movies'),
            trailing: Icon(Icons.chevron_right),
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => const AddMoviesPage()),
              );
            },
          ),
          Text('Statistics'),
        ],
      ),
    );
  }
}
